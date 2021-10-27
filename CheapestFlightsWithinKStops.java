// O(n^k) time, O(n^2) space
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int cheapestPrice = Integer.MAX_VALUE;
        
        // Convert the graph into an adjacency list.
        // Map each city id to a list of its possible destinations: (city id, price)
        HashMap<Integer, LinkedList<Pair<Integer, Integer>>> adjacencyList = new HashMap<Integer, LinkedList<Pair<Integer, Integer>>>();
        
        // Iterate through each flight.
        for (int[] flight : flights) {
            int fromId = flight[0];
            int toId = flight[1];
            int price = flight[2];
            
            // Get the existing list of destinations from the current fromId.
            LinkedList<Pair<Integer, Integer>> destinations = adjacencyList.getOrDefault(fromId, new LinkedList<Pair<Integer, Integer>>());
            destinations.add(new Pair<Integer, Integer>(toId, price));
            
            // Update the adjacency list.
            adjacencyList.put(fromId, destinations);
        }
        
        // Maps a Destination (Stop Id, # of Stops) to the shortest distance to that destination.
        HashMap<Pair<Integer, Integer>, Integer> destShortestPrice = new HashMap<Pair<Integer, Integer>, Integer>();
        destShortestPrice.put(new Pair<Integer, Integer>(src, 0), 0);
        
        Queue<Pair<Integer, Integer>> queue = new LinkedList<Pair<Integer, Integer>>();
        queue.add(new Pair<Integer, Integer>(src, 0));
        
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> currStop = queue.poll();
            int cityId = currStop.getKey();
            int numStops = currStop.getValue();
            int minPriceToCurr = destShortestPrice.get(new Pair<Integer, Integer>(cityId, numStops));
            
            // We have reached the destination.
            if (cityId == dst) {
                cheapestPrice = Math.min(cheapestPrice, minPriceToCurr);
            }
            else {
                // Check that we have NOT exceeded the stop capacity.
                if (numStops <= k) {
                    // Retrieve the list of destinations that we can go to from the current city.
                    LinkedList<Pair<Integer, Integer>> possibleDestinations = adjacencyList.getOrDefault(cityId, new LinkedList<Pair<Integer, Integer>>());
                    
                    // Iterate through the possible destinations we can visit.
                    for (Pair<Integer, Integer> destination : possibleDestinations) {
                        // Increment the number of stops.
                        int newStops = numStops + 1;
                        
                        // Add to the price.
                        int flightPrice = destination.getValue();
                        int newPrice = minPriceToCurr + flightPrice;
                        
                        // Retrieve the flight's destination.
                        int flightDestination = destination.getKey();
                        
                        // Create the Pair representing the destination.
                        Pair<Integer, Integer> destinationPair = new Pair<Integer, Integer>(flightDestination, newStops);
                        
                        // Update the cheapest price to the destination.
                        int cheapestPriceToDest = destShortestPrice.getOrDefault(destinationPair, Integer.MAX_VALUE);
                        
                        if (newPrice < cheapestPriceToDest) {
                            cheapestPriceToDest = Math.min(cheapestPriceToDest, newPrice);
                            destShortestPrice.put(destinationPair, cheapestPriceToDest);

                            // Add the new destination to our queue.
                            queue.add(destinationPair);
                        }
                    }
                }
            }
        }
        
        return cheapestPrice == Integer.MAX_VALUE ? -1 : cheapestPrice;
    }
}