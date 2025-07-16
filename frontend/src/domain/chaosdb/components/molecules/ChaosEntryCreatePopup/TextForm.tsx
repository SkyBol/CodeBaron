import { TextField } from "@mui/material";

interface TextFormProps {
    formik: any
}

const TextForm = ({ formik } : TextFormProps) => {
    if (!formik || !formik.values) {
        return null;
    }

    return (
        <div style={{paddingTop: "20px"}}>
            <TextField
                id="title"
                label="Title"
                value={formik.values.title}
                onChange={formik.handleChange}
            />
            <TextField
                sx={{width: "50%"}}
                id="textContent"
                label="Text Content"
                value={formik.values.textContent}
                onChange={formik.handleChange}
                multiline
                minRows={3}
            />
        </div>
    )
}

export default TextForm;