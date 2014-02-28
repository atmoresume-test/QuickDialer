package ru.jdev.qd.selection;

import ru.jdev.qd.model.ContactInfo;

import java.util.*;

import static java.lang.Long.signum;

public class RecentFirstSelection implements SelectionStrategy {

	private final int recentContactsCount;
	private final int favoritesContactsCount;

	public RecentFirstSelection(int recentContactsCount, int favoritesContectsCount) {
		this.recentContactsCount = recentContactsCount;
		this.favoritesContactsCount = favoritesContectsCount;
	}

	@Override
	public List<ContactInfo> selectContactInfos(Iterable<ContactInfo> allContactInfos) {
		final int totalContacts = recentContactsCount + favoritesContactsCount;
		final LinkedHashSet<ContactInfo> res = new LinkedHashSet<ContactInfo>(totalContacts);

		copy(sortedBy(allContactInfos, new ByLastCallCmp()), res, recentContactsCount);
		copy(sortedBy(allContactInfos, new ByUsageCmp()), res, totalContacts);

		return new ArrayList<ContactInfo>(res);
	}

	private Iterator<ContactInfo> sortedBy(Iterable<ContactInfo> contactInfos, Comparator<ContactInfo> comparator) {
		final Set<ContactInfo> res = new TreeSet<ContactInfo>(comparator);

		for (ContactInfo ci : contactInfos) {
			res.add(ci);
		}

		return res.iterator();
	}

	private <E> void copy(Iterator<E> from, Collection<E> to, int until) {
		while (to.size() < until && from.hasNext()) {
			to.add(from.next());
		}
	}

	private final static class ByUsageCmp implements Comparator<ContactInfo> {

		public int compare(ContactInfo lhs, ContactInfo rhs) {
			return rhs.getUsage() - lhs.getUsage();
		}
	}

	private final static class ByLastCallCmp implements Comparator<ContactInfo> {

		public int compare(ContactInfo lhs, ContactInfo rhs) {
			return signum(rhs.getUsage() - lhs.getUsage());
		}
	}

}
