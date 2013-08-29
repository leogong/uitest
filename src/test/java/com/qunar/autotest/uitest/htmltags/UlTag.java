package com.qunar.autotest.uitest.htmltags;

import org.htmlparser.tags.CompositeTag;

public class UlTag extends CompositeTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8678174509641137991L;
	private static final String mIds[] = { "ul" };
	private static final String mEndTagEnders[] = { "ul" };

	public UlTag() {
	}

	public String[] getIds() {
		return mIds;
	}

	public String[] getEndTagEnders() {
		return mEndTagEnders;
	}
}
