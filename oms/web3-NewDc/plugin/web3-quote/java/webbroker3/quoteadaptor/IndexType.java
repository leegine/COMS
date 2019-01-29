head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	IndexType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����T�[�r�X�Ŏ戵���w���̎�ނ��`�����N���X(IndexType.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/31 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;

/**
 * �����T�[�r�X�Ŏ戵���w���̎�ނ��`�����N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class IndexType extends Enumerated
{

    /**
     * IndexType�Ŏg�p����Enumerated�̐����l���`�����N���X
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues
    {

        /**
         * ����`
         */
        public static final int UNDEFINED = 0;

        /**
         * ���o����
         */
        public static final int NIKKEI225_INDEX = 1;

        /**
         * TOPIX
         */
        public static final int TOPIX = 2;

        /**
         * ���o300�w��
         */
        public static final int NIKKEI300_INDEX = 3;

        /**
         * �X�������w��
         */
        public static final int JASDAQ_INDEX = 4;

        /**
         * ����2�������w��
         */
        public static final int TSE_2ND_SECTION_INDEX = 5;

    };

    /**
     * �����T�[�r�X��Ŏg�p��������R�[�h���`�����萔�N���X<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    private static final class ProductCodes
    {

        /**
         * ����`
         */
        private static final String UNDEFINED = "";

        /**
         * ���o����
         */
        private static final String NIKKEI225_INDEX = "0018";

        /**
         * TOPIX
         */
        private static final String TOPIX = "0005";

        /**
         * ���o300
         */
        private static final String NIKKEI300_INDEX = "0016";

        /**
         * �X�������w��
         */
        private static final String JASDAQ_INDEX = "9004";

        /**
         * ����2�������w��
         */
        private static final String TSE_2ND_SECTION_INDEX = "9005";

    };

    /**
     * ����`
     */
    public static final IndexType UNDEFINED =
        new IndexType(IntValues.UNDEFINED, "UNDEFINED", ProductCodes.UNDEFINED);

    /**
     * ���o����
     */
    public static final IndexType NIKKEI225_INDEX =
        new IndexType(
            IntValues.NIKKEI225_INDEX,
            "NIKKEI225_INDEX",
            ProductCodes.NIKKEI225_INDEX);

    /**
     * TOPIX
     */
    public static final IndexType TOPIX =
        new IndexType(IntValues.TOPIX, "TOPIX", ProductCodes.TOPIX);

    /**
     * ���o300�w��
     */
    public static final IndexType NIKKEI300_INDEX =
        new IndexType(
            IntValues.NIKKEI300_INDEX,
            "NIKKEI300_INDEX",
            ProductCodes.NIKKEI300_INDEX);

    /**
     * �X�������w��
     */
    public static final IndexType JASDAQ_INDEX =
        new IndexType(
            IntValues.JASDAQ_INDEX,
            "JASDAQ_INDEX",
            ProductCodes.JASDAQ_INDEX);

    /**
     * ����2�������w��
     */
    public static final IndexType TSE_2ND_SECTION_INDEX =
        new IndexType(
            IntValues.TSE_2ND_SECTION_INDEX,
            "TSE_2ND_SECTION_INDEX",
            ProductCodes.TSE_2ND_SECTION_INDEX);

    /**
     * �����T�[�r�X��Ŏg�p��������R�[�h
     */
    private final String productCode;

    /**
     * �R���X�g���N�^
     * 
     * @@param i �����l
     * @@param s ������l
     * @@param productCode �����T�[�r�X��Ŏg�p��������R�[�h
     */
    public IndexType(int i, String s, String productCode)
    {
        super(i, s);
        this.productCode = productCode;
    }

    public String getProductCode()
    {
        return productCode;
    }

}
@
