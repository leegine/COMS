head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/01/17 �И���(���u) �V�K�쐬 �d�l�ύX���f��No.179
*/
package webbroker3.eqtypeadmin.service.delegate;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;

/**
 * (�������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X)<BR>
 * �������ϕۏ؋��ێ�������i��ԁj�������쐬�ꌏ�T�[�r�X<BR>
 * <BR>
 * @@author �И���
 * @@version 1.0
 */
public interface WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService
    extends WEB3AdminEquityForcedSettleTempOrderCreateUnitService
{
    /**
     * (validate���ʋ�������)<BR>
     * ����ۗL����ڋq���{�ؕs�����ǂ����̔�����s���B<BR>
     * <BR>
     * @@param l_eqtypeContractRow - ����Row<BR>
     * @@return WEB3TPContractForcedSettleResult
     * @@throws WEB3BaseException
     */
    public WEB3TPContractForcedSettleResult validateContractForcedSettle(EqtypeContractRow l_eqtypeContractRow)
        throws WEB3BaseException;
}
@
