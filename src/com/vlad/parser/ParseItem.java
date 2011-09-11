package com.vlad.parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class ParseItem {
	
	private List<ParseItem> mChildren;
	private ParseItem mParent;
	private StringBuilder mData;
	private int mStartPosition;
	private int mEndPosition;
	
	public ParseItem(StringBuilder data, int startPosition, ParseItem parent) {
		mData = data;
		mParent = parent;
		mStartPosition = startPosition;
		mChildren = new ArrayList<ParseItem>();
	}
	
	public ParseItem getParent() {
		return mParent;
	}
	
	public List<ParseItem> getChildren() {
		return mChildren;
	}
	
	public void setEndPosition(int endPosition) {
		mEndPosition = endPosition;
	}
	
	public boolean isAttribute() {
		return mChildren.isEmpty();
	}
	
	public String getName() throws ParseException {
		for (int i = mStartPosition; i < mEndPosition; i++) {
			char currentCharacter = mData.charAt(i);
			
			if (Character.isWhitespace(currentCharacter)) {
				return mData.substring(mStartPosition + 1, i);
			}
		}
		throw new ParseException("Invalid format: ", mStartPosition);
	}
	
	public String getValue() throws ParseException {
		for (int i = mStartPosition; i < mEndPosition; i++) {
			char currentCharacter = mData.charAt(i);
			
			if (Character.isWhitespace(currentCharacter)) {
				return mData.substring(i + 1, mEndPosition).trim();
			}
		}
		throw new ParseException("Invalid format: ", mStartPosition);
	}
}
