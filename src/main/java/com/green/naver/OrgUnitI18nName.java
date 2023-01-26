package com.green.naver;

import org.springframework.boot.autoconfigure.condition.ConditionalOnSingleCandidate;

@ConditionalOnSingleCandidate
public class OrgUnitI18nName {
	private String language;
	private String name;
}
