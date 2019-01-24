/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : PurposeType�N���X(PurposeType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/02/07 �V���@�h�O(FLJ) �V�K�쐬
 */
package jp.co.dir.dot.intellioms.enums;

import com.fitechlabs.fin.intellioms.enums.Enum;


/**
 *
 *
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public final class PurposeType extends Enum
{

    public static final class IntValues
    {
        public static final int FETCH = 1;
        public static final int PRICE_OBSERVE = 2;
    }

    public static final PurposeType FETCH = new PurposeType(IntValues.FETCH, "FETCH");
    public static final PurposeType PRICE_OBSERVE = new PurposeType(IntValues.PRICE_OBSERVE, "PRICE_OBSERVE");

    /**
     * @param v
     * @param s
     */
    private PurposeType(int v, String s)
    {
        super(v, s);
    }

    public static PurposeType toPurposeType(int intValue)
    {
        switch (intValue)
        {
            case IntValues.FETCH : return FETCH;
            case IntValues.PRICE_OBSERVE : return PRICE_OBSERVE;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }

    public static OmsCondOrderType toOmsCondOrderType(int intValue)
    {
        switch (intValue)
        {
            case IntValues.PRICE_OBSERVE : return OmsCondOrderType.PRICE;
            default :
                throw new IllegalArgumentException("Undefined intValue. [intValue=" + intValue + "]");
        }
    }
}
