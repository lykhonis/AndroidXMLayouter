package com.vlad.parser;

import java.text.ParseException;

public class Parser {

	private ParseItem mRootItem;
	
	public Parser(StringBuilder data) throws ParseException {
		mRootItem = parse(data);
	}
	
	public ParseItem getRootItem() {
		return mRootItem;
	}
	
	private ParseItem parse(StringBuilder data) throws ParseException {
		ParseItem lastItem = null;
		ParseItem item = null;
		for (int i = 0; i < data.length(); i++) {
			char currentCharacter = data.charAt(i);

			if (currentCharacter == '(') {
				if (item != null) {
					lastItem = item;
				}
				
				item = new ParseItem(data, i, lastItem);
				
				if (lastItem != null) {
					lastItem.getChildren().add(item);
				}
			} else if (currentCharacter == ')') {
				if (item == null) {
					throw new ParseException("Invalid format: ", i);
				}
				
				item.setEndPosition(i);
				
				if (lastItem != null) {
					item = lastItem;
					lastItem = item.getParent();
				}
			}
		}
		return item;
	}
}
