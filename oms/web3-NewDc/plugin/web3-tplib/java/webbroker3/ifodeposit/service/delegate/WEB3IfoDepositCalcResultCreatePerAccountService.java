head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.10.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositCalcResultCreatePerAccountService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3IfoDepositCalcResultCreatePerAccountService�N���X(WEB3IfoDepositCalcResultCreatePerAccountService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/24 ��(FLJ) �V�K�쐬
*/

package webbroker3.ifodeposit.service.delegate;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifodeposit.data.IfoDepositCalcResultParams;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * (�ڋq�؋����v�Z���ʍ쐬�T�[�r�X)<BR>
 * �ڋq�؋����v�Z���ʍ쐬�T�[�r�X�C���^�t�F�[�X�B<BR>
 * 
 * 
 * @@author ��(FLJ)
 * @@version 1.0
 */
public interface WEB3IfoDepositCalcResultCreatePerAccountService extends Service
{
    /**
     * �ڋq�؋����v�Z���ʍ쐬�T�[�r�X�����{����B
     * 
     * @@param l_request
     *  - (���N�G�X�g�f�[�^)
     * ���N�G�X�g
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@throws webbroker3.common.WEB3SystemLayerException
     */
    public IfoDepositCalcResultParams createIfoDepositCalcResult(WEB3GentradeSubAccount l_subAccount) throws WEB3SystemLayerException;

}
@
