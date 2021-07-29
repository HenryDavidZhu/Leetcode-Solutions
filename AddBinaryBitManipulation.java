import java.math.BigInteger;

class Solution {
    public String addBinary(String a, String b) {
        BigInteger aInt = new BigInteger(a, 2);
        BigInteger bInt = new BigInteger(b, 2);
        
        BigInteger additionNoCarry = aInt.xor(bInt);
        BigInteger carry = aInt.and(bInt);
        
        while (carry.signum() != 0) {
            carry = carry.shiftLeft(1);
            BigInteger newAdditionNoCarry = additionNoCarry.xor(carry);
            carry = additionNoCarry.and(carry);
            additionNoCarry = newAdditionNoCarry;
        }
        
        return additionNoCarry.toString(2);
    }
}