public class FactorialFinder {

	public static void main(String[] aSt) {
		if (aSt.length != 1) {
			System.out.println("Please enter a non-negative integer.");
			return;
		}
		
		FactorialFinder ff = new FactorialFinder(aSt[0]);
		ff.findFactorial();
	}

	private int m_nValue;
	private byte[] m_aBProduct;

	public FactorialFinder(int nValue) {
		if (nValue < 0)
			throw new IllegalArgumentException("Factorial of negative number is undefined.");
		
		m_nValue = nValue;
	}

	public FactorialFinder(String stValue) {
		this(Integer.parseInt(stValue));
	}

	public void findFactorial() {
		if (m_nValue == 0) {
			System.out.println("0! = 1");
			return;
		}

		m_aBProduct = getDigitsArray(m_nValue);
		for (int nValue = m_nValue - 1; nValue > 1; nValue--) {
			if (nValue % 100 == 0)
				System.out.println("Multiplying by " + nValue);

			m_aBProduct = multiplyFactors(m_aBProduct, getDigitsArray(nValue));
		}

		printResults();
	}

	private byte[] getDigitsArray(int n) {
		int nDigits = 1;
		for (int nOrderOfMag = 10; nOrderOfMag <= n; nOrderOfMag *= 10) {
			nDigits++;
		}

		byte[] aBDigits = new byte[nDigits];
		int nOrderOfMag = 1, nIdxDigit = nDigits - 1;
		do {
			aBDigits[nIdxDigit--] = (byte) (n / nOrderOfMag % 10);
			nOrderOfMag *= 10;
		} while (nOrderOfMag <= n);

		return aBDigits;
	}

	private byte[] multiplyFactors(byte[] aB1, byte[] aB2) {
		int nSizeAB1 = aB1.length;
		int nSizeAB2 = aB2.length;
		byte[] aBResult = new byte[nSizeAB1 + nSizeAB2];

		for (int nIdxB1 = nSizeAB1 - 1; nIdxB1 >= 0; nIdxB1--) {
			byte b1 = aB1[nIdxB1];
			for (int nIdxB2 = nSizeAB2 - 1; nIdxB2 >= 0; nIdxB2--) {
				addToByte(aBResult, b1 * aB2[nIdxB2], nIdxB1 + nIdxB2 + 1);
			}
		}

		return aBResult;
	}

	private void addToByte(byte[] aB, int nNewVal, int nIdx) {
		int nNewSum = aB[nIdx] + nNewVal;
		aB[nIdx] = (byte) (nNewSum % 10);

		if (nNewSum >= 10)
			addToByte(aB, nNewSum / 10, nIdx - 1);
	}

	private void printResults() {
		System.out.print(m_nValue + "! = ");

		boolean fPassedLeadingZeros = false;
		for (byte digit : m_aBProduct) {
			if (!fPassedLeadingZeros) {
				if (digit != 0) {
					System.out.print(digit);
					fPassedLeadingZeros = true;
				}

			} else
				System.out.print(digit);
		}

		System.out.println();
	}
}