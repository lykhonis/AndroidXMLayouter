package com.vlad.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import com.vlad.Converter;

public class GenerateXML implements IObjectActionDelegate {

	@Override
	public void run(IAction action) {
		Converter.convertInsideCurrentEditor();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void setActivePart(IAction action, IWorkbenchPart targetPart) {
	}
}
