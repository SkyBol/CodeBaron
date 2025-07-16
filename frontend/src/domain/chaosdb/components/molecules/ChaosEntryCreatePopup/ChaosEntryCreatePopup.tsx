import { Close, Save } from "@mui/icons-material";
import { Button, IconButton, MenuItem, Select, Typography } from "@mui/material";
import styled from "styled-components";
import { useFormik } from 'formik';
import TextForm from "./TextForm";
import ChaosEntryService from "../../../services/ChaosEntryService";
import FileForm from "./FileForm";

interface ChaosEntryCreatePopupProps {
    isOpen?: boolean;
    setOpen: (open: boolean) => void;
}

const ChaosEntryCreatePopup = ({ isOpen, setOpen } : ChaosEntryCreatePopupProps) => {
    const closePopup = () => setOpen(false);

    const formik = useFormik({
        initialValues: {
            type: "FILE",
            textContent: "",
            title: "",
            images: [],
        },
        onSubmit: async (values) => {
            if (values.type === 'TEXT') {
                ChaosEntryService.postText({
                    mediaType: 'TEXT',
                    textContent: values.textContent,
                    title: values.title,
                })
                    .then(closePopup)
                    .catch(console.error)
            } else if (values.type === 'FILE') {
                if (values.images === null || values.images === undefined) {
                    console.log("images null");
                } else {
                    Promise.all(
                        (Array.from(values.images))
                            .map(async (image: any) =>
                                await ChaosEntryService
                                    .uploadFile(image)
                                    .catch(console.error)
                            )
                    )
                    .then(closePopup)
                    .catch(console.error)
                }
            }

            
        }
    });

    const typeForm = () => {
        if (formik.values.type === 'TEXT') {
            return <TextForm formik={formik} />
        } else if (formik.values.type === 'FILE') {
            return <FileForm formik={formik} />
        }
    }

    return (
        <div>
            {/* Popup */}
            {
                isOpen && (
                    <StyledPopupBackground onClick={closePopup}>
                        <StyledPopupContainer onClick={(event) => event.stopPropagation()}>
                            {/* Header for Popup */}
                            <StyledPopupContainerHeader>
                                <Typography variant="h6">Add new Entry</Typography>
                                <IconButton onClick={closePopup}><Close/></IconButton>
                            </StyledPopupContainerHeader>
                            {/* Body of Popup */}
                            <StyledPopupContainerBody>
                                <Select
                                    label="Content Type"
                                    id="type"
                                    value={formik.values.type}
                                    onChange={(e: any) => formik.setFieldValue("type", e.target.value)}
                                    sx={{width: "200px"}}
                                >
                                    <MenuItem value="FILE">File</MenuItem>
                                    <MenuItem value="TEXT">Text</MenuItem>
                                </Select>
                                {typeForm()}
                                <SaveButtonContainer>
                                    <Button variant="contained" onClick={formik.submitForm}>
                                        <Save />
                                        Save
                                    </Button>
                                </SaveButtonContainer>
                            </StyledPopupContainerBody>
                        </StyledPopupContainer>
                    </StyledPopupBackground>
                )
            }
            {/* File Dropdown: TODO */}
        </div>
    );
}

const SaveButtonContainer = styled.div`
    position: absolute;
    bottom: 20px;
    right: 20px;
`;
const StyledPopupContainerBody = styled.div``;

const StyledPopupContainerHeader = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
`;

const StyledPopupContainer = styled.div`
    position: absolute;
    background-color: white;
    top: 20px;
    bottom: 20px;
    right: 20px;
    left: 20px;
    color: black;
    padding: 15px;
`;

const StyledPopupBackground = styled.div`
    background-color: rgba(0, 0, 0, 0.4);
    position: fixed;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    z-index: 100;
`;

export default ChaosEntryCreatePopup;