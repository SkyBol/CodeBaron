
interface FileFormProps {
    formik: any;
}

const FileForm = ({ formik } : FileFormProps) => {
    return (
        <div style={{paddingTop: "20px"}}>
            <input
                type="file"
                name="images"
                accept="*"
                onChange={(e) => {
                    if (e.currentTarget.files) {
                        formik.setFieldValue("images", e.currentTarget.files);
                    }
                }}
                multiple
            />
        </div>
    )
}

export default FileForm;