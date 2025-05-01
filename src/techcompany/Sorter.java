package techcompany; // Declares the package name

import java.util.*; // Imports utilities, including List and ArrayList

// Sorter class containing a merge sort implementation for Employee lists
public class Sorter {

    // Public method to perform merge sort on a list of Employees
    public static List<Employee> mergeSort(List<Employee> list) {
        if (list.size() <= 1) return list; // Base case: if list has 0 or 1 item, it's already sorted

        int mid = list.size() / 2; // Find the middle index
        // Recursively sort left and right halves of the list
        List<Employee> left = mergeSort(list.subList(0, mid));
        List<Employee> right = mergeSort(list.subList(mid, list.size()));

        return merge(left, right); // Merge the sorted halves
    }

    // Helper method to merge two sorted lists into one
    private static List<Employee> merge(List<Employee> left, List<Employee> right) {
        List<Employee> result = new ArrayList<>(); // Result list to store merged employees
        int i = 0, j = 0; // Indexes for left and right lists

        // Loop to compare elements from both lists and add the smaller one to the result
        while (i < left.size() && j < right.size()) {
            // Compare names case-insensitively
            if (left.get(i).getName().compareToIgnoreCase(right.get(j).getName()) <= 0) {
                result.add(left.get(i++)); // Add from left if name is smaller or equal
            } else {
                result.add(right.get(j++)); // Otherwise add from right
            }
        }

        // Add any remaining elements from left and right lists
        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));

        return result; // Return the final sorted list
    }
}
