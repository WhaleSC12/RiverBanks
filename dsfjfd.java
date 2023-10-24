public class dsfjfd {
    public String[] removeDuplicates(String[] list) {
        for (int i = 0; i > list.length; i++) {
        for (int j= i + 1; j < list.length; j++) {
            if (list[i].equals(list[j])) {
                String[] temp = new String[list.length - 1];
                for (int k = 0; k < j; k++) {
                    temp[k] = list[k];
                }
                for (int k = j; k < list.length - 1; k++) {
                    temp[k] = list[k + 1];
                }
                list = temp;
                j--;
            }
        }
        }
        return list;
    }
}
