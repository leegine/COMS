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
filename	WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ϕۏ؋��ێ�������i�I�����C���J�n�O�j�������쐬�ꌏ�T�[�r�X(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 �����q(���u) �V�K�쐬 �d�l�ύX���f��No.131
Revision History : 2007/08/21 �đo�g(���u) �d�l�ύX���f��No.162
*/

package webbroker3.eqtypeadmin.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;


/**
 * (�������ϕۏ؋��ێ�������i�I�����C���J�n�O�j�������쐬�ꌏ�T�[�r�X)<BR>
 * �������ϕۏ؋��ێ�������i�I�����C���J�n�O�j�������쐬�ꌏ�T�[�r�X<BR>
 * <BR>
 * @@author �����q
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService
    extends WEB3AdminEquityForcedSettleTempOrderCreateUnitService
{
    /**
     * (validate���ʋ�������)<BR>
     * ����ۗL����ڋq���{�ؕs�����ǂ����̔�����s��<BR>
     * @@param l_eqtypeContractRow - (����Row)<BR>
     * ����Row<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettle(EqtypeContractRow l_eqtypeContractRow)
        throws WEB3BaseException;
}
@
