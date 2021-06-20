class DS {
    public Map<Integer, Integer> parents;
    
    public DS(int numAccounts) {
        this.parents = new HashMap<Integer, Integer>();
        
        for (int i = 0 ; i < numAccounts; i++) {
            this.parents.put(i, i);
        }
    }
    
    public int findRoot(int index) {
        while (this.parents.get(index) != index) {
            index = this.parents.get(index);
        }
        
        return index;
    }
    
    public int union(int index1, int index2) {
        int index1Root = findRoot(index1);
        int index2Root = findRoot(index2);
        
        this.parents.put(index1Root, index2Root);
        return index2Root;
    }
}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // Construct disjoint set graph of merged accounts
        DS accountIdxDS = new DS(accounts.size());
        Map<String, Integer> email2AccountIdx = new HashMap<String, Integer>();
        
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            List<String> emails = account.subList(1, account.size());
            
            for (String email : emails) {
                Integer emailAccountIdx = email2AccountIdx.getOrDefault(email, null);
                
                // Email is not found within another account
                if (emailAccountIdx == null) {
                    email2AccountIdx.put(email, accountIdxDS.findRoot(i));
                }
                // Email is already found within another acount
                else {
                    email2AccountIdx.put(email, accountIdxDS.union(i, emailAccountIdx));
                }
            }
        }
        
        // Convert disjoint set graph to a mapping of account indexes to their emails
        Map<Integer, List<String>> accountIdx2Emails = new HashMap<Integer, List<String>>();
        
        for (Map.Entry<String, Integer> emailAndAccountIdx : email2AccountIdx.entrySet()) {
            String email = emailAndAccountIdx.getKey();
            Integer accountIdx = accountIdxDS.findRoot(emailAndAccountIdx.getValue());
            List<String> accountEmails = new LinkedList<String>();
            
            if (!accountIdx2Emails.containsKey(accountIdx)) {
                accountEmails.add(email);
                accountIdx2Emails.put(accountIdx, accountEmails);
            }
            else {
                accountEmails = accountIdx2Emails.get(accountIdx);
                accountEmails.add(email);
                accountIdx2Emails.put(accountIdx, accountEmails);
            }
        }
        
        // Convert mapping of account indexes to their emails to correct list format
        List<List<String>> mergedAccounts = new LinkedList<List<String>>();
        
        for (Map.Entry<Integer, List<String>> accountIdxAndEmails : accountIdx2Emails.entrySet()) {
            Integer accountIdx = accountIdxAndEmails.getKey();
            List<String> emails = accountIdxAndEmails.getValue();
            Collections.sort(emails);
            
            List<String> account = new LinkedList<String>();
            account.add(accounts.get(accountIdx).get(0));
            account.addAll(emails);
            
            mergedAccounts.add(account);
        }
        
        return mergedAccounts;
    }
}