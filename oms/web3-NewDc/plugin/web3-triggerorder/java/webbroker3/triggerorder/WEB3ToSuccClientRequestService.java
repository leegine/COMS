head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�������N���C�A���g���N�G�X�g�T�[�r�X(WEB3ToSuccClientRequestService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/12 �Г�(���u) �V�K�쐬
                 : 2006/10/30 ������(���u)�@@���f��No.160
*/

package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CommodityDivDef;
import webbroker3.gentrade.WEB3ClientRequestService;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (�A�������N���C�A���g���N�G�X�g�T�[�r�X)<BR>
 * �A�������N���C�A���g���N�G�X�g�T�[�r�X
 * <BR>
 * @@author �Г� <BR>
 * @@version 1.0<BR>
 */
public class WEB3ToSuccClientRequestService extends WEB3ClientRequestService 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccClientRequestService.class);
    
    /**
     * @@roseuid 4348D89A035B
     */
    public WEB3ToSuccClientRequestService() 
    {

    }
    
    /**
     * (get�⏕����)<BR>
     * �igetSubAccount()�̃I�[�o�[���[�h�j<BR>
     * �����̏��i�敪�ɊY������⏕�������擾����B<BR>
     * <BR>
     * �P�j�⏕�����^�C�v�����肷��B<BR>
     * <BR>
     * �@@�p�����[�^.���i�敪���A<BR>
     * �@@�@@["��������"�̏ꍇ]<BR>
     * �@@�@@�@@[�M�p�q(*1)�̏ꍇ]<BR>
     * �@@�@@�@@�@@�⏕�����^�C�v = SubAccountTypeEnum.�M�p�������<BR>
     * �@@�@@�@@[�M�p�q�ȊO�̏ꍇ]<BR>
     * �@@�@@�@@�@@�⏕�����^�C�v = SubAccountTypeEnum.�����������<BR>
     * <BR>
     * �@@�@@["�M�p���"�̏ꍇ]<BR>
     * �@@�@@�@@�⏕�����^�C�v = SubAccountTypeEnum.�M�p�������<BR>
     * <BR>
     * �@@�@@["�敨"�̏ꍇ]<BR>
     * �@@�@@�@@�⏕�����^�C�v = SubAccountTypeEnum.�؋�������<BR>
     * <BR>
     * �@@�@@["�I�v�V����"�̏ꍇ]<BR>
     * �@@�@@�@@�⏕�����^�C�v = �ڋq(*2).getOP��������^�C�v()<BR>
     * <BR>
     * �Q�jsuper.get�⏕����()�ɂĕ⏕�������擾���A<BR>
     * �@@�ԋp����B<BR>
     * <BR>
     * �@@[get�⏕����()�Ɏw�肷�����]<BR>
     * �@@�@@�⏕�����^�C�v�F�@@���肵���⏕�����^�C�v<BR>
     * <BR>
     * (*1)�M�p�q<BR>
     * �@@super.get����().is�M�p�����J��()�̖߂�l == true�̏ꍇ<BR>
     * <BR>
     * �@@[is�M�p�����J��()�Ɏw�肷�����]<BR>
     * �@@�@@�ٍϋ敪�F�@@"�w��Ȃ�"<BR>
     * <BR>
     * (*2)�ڋq<BR>
     * �@@super.get����()�ɂĎ擾<BR>
     * @@param l_strCommodityDiv ���i�敪<BR>
     * ���i�敪<BR>
     * <BR>
     * 1�F�@@��������<BR>
     * 2�F�@@�M�p���<BR>
     * 3�F�@@�敨<BR>
     * 4�F�@@�I�v�V����<BR>
     * <BR> 
     * @@return WEB3GentradeSubAccount
     * @@throws WEB3BaseException
     * @@roseuid 431D6AE60124
     */
    public WEB3GentradeSubAccount getSubAccount(String l_strCommodityDiv) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getSubAccount(String)";
        log.entering(STR_METHOD_NAME);
        
        SubAccountTypeEnum l_subAccountTypeEnum = null;
        WEB3GentradeSubAccount l_subAccount = null;
        
        //�P�j�⏕�����^�C�v�����肷��B 
        // �p�����[�^.���i�敪���A 
        //   ["��������"�̏ꍇ] 
        if (WEB3CommodityDivDef.EQUITY.equals(l_strCommodityDiv))
        {
            //[�M�p�q(*1)�̏ꍇ] 
            if (((WEB3GentradeMainAccount)super.getMainAccount()).isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
            {
                //�⏕�����^�C�v = SubAccountTypeEnum.�M�p������� 
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
            }
            else
            {
                //�⏕�����^�C�v = SubAccountTypeEnum.�����������
                l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
            }
        }
        //"�M�p���"�̏ꍇ
        else if (WEB3CommodityDivDef.MARGIN.equals(l_strCommodityDiv))
        {
            //�⏕�����^�C�v = SubAccountTypeEnum.�M�p������� 
            l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
        }
        //"�敨"�̏ꍇ
        else if (WEB3CommodityDivDef.FUTURE.equals(l_strCommodityDiv))
        {
            //�⏕�����^�C�v = SubAccountTypeEnum.�؋�������
            l_subAccountTypeEnum = SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT;
        }
        //"�I�v�V����"�̏ꍇ
        else if (WEB3CommodityDivDef.OPTION.equals(l_strCommodityDiv))
        {
            //�⏕�����^�C�v = �ڋq(*2).getOP��������^�C�v() 
            l_subAccountTypeEnum = 
                ((WEB3GentradeMainAccount)super.getMainAccount()).getOpSubAccountType();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //�Q�jsuper.get�⏕����()�ɂĕ⏕�������擾���A�ԋp����B 
        l_subAccount = 
            (WEB3GentradeSubAccount)super.getSubAccount(l_subAccountTypeEnum);
        
        log.exiting(STR_METHOD_NAME);
        return l_subAccount;
    }
}
@
