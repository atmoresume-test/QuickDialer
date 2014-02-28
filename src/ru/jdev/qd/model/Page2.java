package ru.jdev.qd.model;

import ru.jdev.qd.selection.SelectionStrategy;

public class Page2 {

	private final int itemsCount;
	private final ContactInfoDao dataSource;
	private final ContactInfo[] items;

	private volatile ContactInfo selectedItem;

	public Page2(int itemsCount, ContactInfoDao dataSource, SelectionStrategy selectionStrategy) {
		this.itemsCount = itemsCount;
		this.dataSource = dataSource;

		items = new ContactInfo[itemsCount];
		update(dataSource, selectionStrategy);
	}

	public void update(ContactInfoDao dataSource, SelectionStrategy selectionStrategy) {
		int idx = 0;
		dataSource.update();
		for (ContactInfo ci: selectionStrategy.selectContactInfos(dataSource.getContactInfoList())) {
			items[idx++] = ci;
		}
	}

	public ContactInfo getItem(int id) {
		return items[id];
	}

	public ContactInfo getSelectedItem() {
		return selectedItem;
	}

	public void selectItem(int id) {
		this.selectedItem = items[id];
	}

}
