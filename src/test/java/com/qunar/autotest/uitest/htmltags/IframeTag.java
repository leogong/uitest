package com.qunar.autotest.uitest.htmltags;

import org.htmlparser.tags.CompositeTag;

public class IframeTag extends CompositeTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8678174509641137991L;
	private static final String mIds[] = { "iframe" };
	private static final String mEndTagEnders[] = { "iframe" };

	public IframeTag() {
	}

	public String[] getIds() {
		return mIds;
	}

	public String[] getEndTagEnders() {
		return mEndTagEnders;
	}
}
