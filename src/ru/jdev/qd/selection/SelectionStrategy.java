package ru.jdev.qd.selection;

import ru.jdev.qd.model.ContactInfo;

import java.util.List;

public interface SelectionStrategy {

	List<ContactInfo> selectContactInfos(Iterable<ContactInfo> allContactInfos);

}
