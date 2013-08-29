package com.qunar.autotest.uitest.htmltags;

import org.htmlparser.tags.CompositeTag;

public class LiTag extends CompositeTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8678174509641137991L;
	private static final String mIds[] = { "li" };
	private static final String mEndTagEnders[] = { "li" };

	public LiTag() {
	}

	public String[] getIds() {
		return mIds;
	}

	public String[] getEndTagEnders() {
		return mEndTagEnders;
	}
}
