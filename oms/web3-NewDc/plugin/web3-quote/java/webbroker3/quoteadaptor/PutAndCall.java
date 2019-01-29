head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.43.44;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	PutAndCall.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������̃v�b�g���R�[����\��Enumerated�N���X(PutAndCall.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/10 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.quoteadaptor;

import com.fitechlabs.xtrade.kernel.enumerated.Enumerated;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;

/**
 * �������̃v�b�g���R�[����\��Enumerated�N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public final class PutAndCall extends Enumerated
{

    /**
     * �v�b�g���R�[����Enumerated�N���X�Ŏg�p����萔<br>
     * 
     * @@author Takuji Yamada
     * @@version 1.0
     */
    public static class IntValues
    {
        /**
         * �v�b�g���R�[���F����`
         */
        public static final int UNDEFINED = 0;

        /**
         * �v�b�g���R�[���F�v�b�g
         */
        public static final int PUT = 1;

        /**
         * �v�b�g���R�[���F�R�[��
         */
        public static final int CALL = 2;

    }

    /**
     * �v�b�g���R�[���F����`
     */
    public static final PutAndCall UNDEFINED =
        new PutAndCall(IntValues.UNDEFINED, "");

    /**
     * �v�b�g���R�[���F�v�b�g
     */
    public static final PutAndCall PUT = new PutAndCall(IntValues.PUT, "P");

    /**
     * �v�b�g���R�[���F�R�[��
     */
    public static final PutAndCall CALL = new PutAndCall(IntValues.CALL, "C");

    /**
     * �R���X�g���N�^
     */
    private PutAndCall(int i, String s)
    {
        super(i, s);
    }
    
    public static PutAndCall getPutAndCall(IfoDerivativeTypeEnum derivativeType)
    {
        switch (derivativeType.intValue())
        {
            case IfoDerivativeTypeEnum.IntValues.PUT_OPTIONS :
                return PUT;
            case IfoDerivativeTypeEnum.IntValues.CALL_OPTIONS :
                return CALL;
            default :
                return UNDEFINED;
        }
    }

}
@
