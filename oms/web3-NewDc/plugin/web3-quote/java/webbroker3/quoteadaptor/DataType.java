head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.07;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	DataType.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 * Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 * File Name        : �����������N���X(DataType.java)
 * Author Name      : Daiwa Institute of Research
 * Revision History : 2004/01/30 �R�c�@@��i(FLJ) �V�K�쐬
 */
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;

/**
 * ��ʃR�[�h��Enum�N���X
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class DataType extends Enumerated
{

    /**
     * ��ʃR�[�h��Enum�N���X�Ŏg�p����萔<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static final class IntValues
    {
        /**
         * ��ʃR�[�h�F����`
         */
        public static final int UNDEFINED = 0;

        /**
         * ��ʃR�[�h�F����
         */
        public static final int EQUITY = 1;

        /**
         * ��ʃR�[�h�F�w��
         */
        public static final int INDEX = 2;

        /**
         * ��ʃR�[�h�F�����w���敨
         */
        public static final int INDEX_FUTURE = 3;

        /**
         * ��ʃR�[�h�F�����w���I�v�V����
         */
        public static final int INDEX_OPTION = 4;

    }

    /**
     * ��ʃR�[�h�F����`
     */
    public static final DataType UNDEFINED =
        new DataType(IntValues.UNDEFINED, "UNDEFINED");

    /**
     * ��ʃR�[�h�F����
     */
    public static final DataType EQUITY =
        new DataType(IntValues.EQUITY, "EQUITY");

    /**
     * ��ʃR�[�h�F�w��
     */
    public static final DataType INDEX = new DataType(IntValues.INDEX, "INDEX");

    /**
     * ��ʃR�[�h�F�����w���敨
     */
    public static final DataType INDEX_FUTURE =
        new DataType(IntValues.INDEX_FUTURE, "INDEX_FUTURE");

    /**
     * ��ʃR�[�h�F�����w���I�v�V����
     */
    public static final DataType INDEX_OPTION =
        new DataType(IntValues.INDEX_OPTION, "INDEX_OPTION");

    private DataType(int i, String s)
    {
        super(i, s);
    }

    /**
     * �����^�C�v����ʃR�[�h�ɕϊ����܂��B<br>
     * 
     * @@param productType �����^�C�v
     * @@return ��ʃR�[�h
     */
    public static DataType toDataType(ProductTypeEnum productType)
    {
        switch (productType.intValue())
        {
            case ProductTypeEnum.IntValues.EQUITY :
                return DataType.EQUITY;
            default :
                return DataType.UNDEFINED;
        }
    }

    public static DataType getDataType(TradedProduct tradedProduct)
    {
        switch (tradedProduct.getProduct().getProductType().intValue())
        {
            case ProductTypeEnum.IntValues.EQUITY :
                return DataType.EQUITY;
            case ProductTypeEnum.IntValues.IFO :
                {
                    IfoProduct product =
                        (IfoProduct) tradedProduct.getProduct();
                    IfoDerivativeTypeEnum derivativeType =
                        product.getDerivativeType();
                    switch (derivativeType.intValue())
                    {
                        case IfoDerivativeTypeEnum.IntValues.FUTURES :
                            return DataType.INDEX_FUTURE;
                        case IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS :
                        case IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS :
                            return DataType.INDEX_OPTION;
                        default :
                            return DataType.UNDEFINED;
                    }
                }
            default :
                return DataType.UNDEFINED;
        }
    }
}
@
