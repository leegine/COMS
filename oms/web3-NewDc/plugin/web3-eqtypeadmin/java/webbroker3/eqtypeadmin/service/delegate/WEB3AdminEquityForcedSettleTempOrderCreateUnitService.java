head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleTempOrderCreateUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ω������쐬�ꌏ�T�[�r�X(WEB3AdminEquityForcedSettleTempOrderCreateUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����q(���u) �V�K�쐬 �d�l�ύX���f��No.131
*/

package webbroker3.eqtypeadmin.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;

/**
 * (�������ω������쐬�ꌏ�T�[�r�X)<BR>
 * �������ω������쐬�ꌏ�T�[�r�X�C���^�[�t�F�[�X<BR>
 * �i�g�����U�N�V���������FTransactionalInterceptor.TX_CREATE_NEW�j<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleTempOrderCreateUnitService extends Service
{
    /**
     * (exec�������쐬)<BR>
     * �������쐬�ꌏ���������{����B<BR>
     * @@param l_eqtypeContractRow - (����Row)<BR>
     * ����Row<BR>
     * @@param l_strForcedSettleReasonType- (�������ϗ��R�敪)<BR>
     * �������ϗ��R�敪<BR>
     * @@param l_contractForceSettleResult - (�������ϗ]�͌v�Z����)<BR>
     * �]�͂Ōv�Z�������ʋ������ό��ʃI�u�W�F�N�g<BR>
     * @@param l_blnIsSuccOrderHandling - (is�A�������戵�\)<BR>
     * �A���������戵�\���ǂ����̃t���O<BR>
     * <BR>
     * true�F�@@�A�������戵�\<BR>
     * false�F�@@�A�������戵�s��<BR>
     * @@roseuid 4610C94E012C
     */
    public void execTempOrderCreation(EqtypeContractRow l_eqtypeContractRow,
        String l_strForcedSettleReasonType,
        WEB3TPContractForcedSettleResult l_contractForceSettleResult,
        boolean l_blnIsSuccOrderHandling) throws WEB3BaseException;
}
@
