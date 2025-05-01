package techcompany; // Declares this class is part of the 'techcompany' package

import java.util.*; // Imports utility classes, including List

// This class handles searching functionality
public class Searcher {

    // Method that performs binary search on a sorted list of Employees
    public static int binarySearch(List<Employee> sortedList, String target) {
        int low = 0, high = sortedList.size() - 1; // Define the search boundaries (start and end of the list)

        // Continue searching while the range is valid
        while (low <= high) {
            int mid = (low + high) / 2; // Find the midpoint index
            // Compare the name at mid with the target name, ignoring case
            int cmp = sortedList.get(mid).getName().compareToIgnoreCase(target);

            if (cmp == 0) return mid;       // If names match, return the index
            else if (cmp < 0) low = mid + 1; // If mid name is less, search right half
            else high = mid - 1;            // If mid name is greater, search left half
        }

        return -1; // Return -1 if the employee is not found
    }
}
