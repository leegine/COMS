head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3GentradeSpanConnectService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : SPAN�ڑ��T�[�r�X(WEB3GentradeSpanConnectService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/09/24 羐� (���u) �V�K�쐬
*/
package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * SPAN�ڑ��T�[�r�X�C���^�[�t�F�C�X
 */
public interface WEB3GentradeSpanConnectService extends Service
{

    /**
     * (callSPAN�؋���) <BR>
     * SPAN�؋�����ԋp����B <BR>
     * @@param l_genSubAccount - �⏕�����I�u�W�F�N�g
     * @@param l_totalContractSpecs
     * @@return double
     * @@roseuid 411C229000DA
     */
    public double callSpanIfoDeposit(
        WEB3GentradeSubAccount l_genSubAccount,
        WEB3GentradeTotalContractSpec[] l_totalContractSpecs);
}
@
