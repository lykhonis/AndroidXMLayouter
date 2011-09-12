package com.vlad;

import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.texteditor.IDocumentProvider;
import org.eclipse.ui.texteditor.ITextEditor;

public class ActiveEditor {

	private static final ActiveEditor mInstance = new ActiveEditor();
	
	private IDocument mDocument;
	
	public static ActiveEditor getInstance() {
		return mInstance;
	}
	
	public ActiveEditor() {
		IEditorPart editorPart = Activator.getDefault().getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.getActiveEditor();

		ITextEditor editor;
		if (editorPart instanceof ITextEditor) {
			editor = (ITextEditor) editorPart;
		} else {
			editor = (ITextEditor) editorPart.getAdapter(ITextEditor.class);
		}

		IDocumentProvider documentProvider = editor.getDocumentProvider();
		mDocument = documentProvider.getDocument(editor.getEditorInput());
	}
	
	public String getContent() {
		return mDocument.get();
	}
	
	public void setContent(String content) {
		mDocument.set(content);
	}
}
