import Storage from "./Storage";

export type ChaosContent = baseObject & (TEXT | FILE)

export type TEXT = { mediaType: 'TEXT'; title: string; textContent: string; }
export type FILE = { mediaType: 'FILE'; storage: Storage; fileType: FileType; }

type FileType = 'IMAGE' | 'AUDIO' | 'VIDEO' | 'DOCUMENT'

type baseObject = { id: string; }

export default ChaosContent;