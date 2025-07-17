import { Typography } from "@mui/material";
import { useEffect } from "react";
import { useDropzone } from "react-dropzone";
import styled from "styled-components";

interface FileFormProps {
    formik: any;
}

const FileForm = ({ formik } : FileFormProps) => {
    const {acceptedFiles, getRootProps, getInputProps} = useDropzone({multiple: true});

    useEffect(() => {
        console.log(acceptedFiles);
        formik.setFieldValue("images", acceptedFiles);
    }, acceptedFiles);

    return (
        <StyledDropzone {...getRootProps()}>
            <input {...getInputProps()}/>
            <Typography>Click to select files</Typography>
        </StyledDropzone>
    )
}

const StyledDropzone = styled.div`
    padding-top: 20px;
`;

export default FileForm;