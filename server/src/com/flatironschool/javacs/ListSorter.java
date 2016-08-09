/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {
	
		for (int i=1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j-1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
        if (list.size() < 2) {
            return list;
        } else if (list.size() == 2) {
            if (comparator.compare(list.get(0), list.get(1)) > 0) {
                Collections.reverse(list);
            }
            return list;
        } else {
            List<T> left = list.subList(0, list.size() / 2);
            List<T> right = list.subList(list.size() / 2, list.size());
            return merge(mergeSort(left, comparator),
                         mergeSort(right, comparator),
                         comparator);
        }
	}

    public List<T> merge(List<T> l1, List<T> l2, Comparator<T> comparator) {
        List<T> ret = new ArrayList<T>(l1.size() + l2.size());
        int i = 0;
        int j = 0;

        while (i < l1.size() && j < l2.size()) {
            T currentI = l1.get(i);
            T currentJ = l2.get(j);

            if (comparator.compare(currentI, currentJ) <= 0) {
                ret.add(currentI);
                i++;
            } else {
                ret.add(currentJ);
                j++;
            }
        }

        while (i < l1.size()) {
            ret.add(l1.get(i++));
        }

        while (j < l2.size()) {
            ret.add(l2.get(j++));
        }

        return ret;
    }

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> heap = new PriorityQueue(list.size(), comparator);
        while (!list.isEmpty()) {
            heap.offer(list.remove(0));
        }
        while (!heap.isEmpty()) {
            list.add(heap.poll());
        }
	}

	
	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return 
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
        PriorityQueue<T> heap = new PriorityQueue<T>(k, comparator);
        for (T element : list) {
            if (heap.size() < k) {
                heap.offer(element);
            } else if (comparator.compare(element, heap.peek()) > 0) {
                heap.poll();
                heap.offer(element);
            }
        }

        List<T> ret = new ArrayList<T>(k);
        while (!heap.isEmpty()) {
            ret.add(heap.poll());
        }

        return ret;
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		
		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};
		
		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);
	
		list = new ArrayList<Integer>(Arrays.asList(6, 3, 5, 8, 1, 4, 2, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
