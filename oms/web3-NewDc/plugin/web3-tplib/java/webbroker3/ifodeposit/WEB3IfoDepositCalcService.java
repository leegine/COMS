head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �؋����v�Z�T�[�r�X�C���^�[�t�F�[�X(WEB3IfoDepositCalcService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/11/08 �R�c�@@��i(FLJ) �V�K�쐬
*/
package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;

/**
 * �؋����v�Z�T�[�r�X�C���^�[�t�F�[�X
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3IfoDepositCalcService extends Service
{

    /**
     * (get�؋����v�Z)<BR>
     * 
     * �؋����v�Z�𐶐����A�ԋp����B<BR>
     * 
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g�B
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalc
     */
    public WEB3IfoDepositCalc getIfoDepositCalc(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException;

    /**
     * (get�؋����v�Z)<BR>
     * 
     * ����̒��������f���ꂽ�؋����v�Z�𐶐����A�ԋp����B<BR>
     * �i�V�K���]�̓`�F�b�N���Ɏg�p�j<BR>
     * 
     * @@param l_subAccount - (�⏕����) �⏕�����I�u�W�F�N�g�B
     * @@param l_ifoNewOrderSpec - �敨OP���������e�B
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalc
     */
    public WEB3IfoDepositCalc getIfoDepositCalc(
        WEB3GentradeSubAccount l_subAccount,
        WEB3IfoNewOrderSpec l_ifoNewOrderSpec)
        throws WEB3SystemLayerException;

    /**
     * (create�؋����v�Z����)<BR>
     * <BR>
     * �؋����v�Z�������쐬����B<BR>
     * <BR>
     * @@param l_subAccount - (�⏕����)<BR>
     * @@return webbroker3.ifodeposit.WEB3IfoDepositCalcCondition
     */
    public WEB3IfoDepositCalcCondition createIfoDepositCalcCondition(WEB3GentradeSubAccount l_subAccount);

}@
