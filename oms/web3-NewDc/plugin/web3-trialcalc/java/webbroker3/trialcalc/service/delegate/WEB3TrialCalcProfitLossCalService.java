head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.50.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcProfitLossCalService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X���v�v�Z�T�[�r�X(WEB3TrialCalcProfitLossCalService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i�v�Z�T�[�r�X���v�v�Z�T�[�r�X�j<BR>
 * <BR>
 * ���v�v�Z�T�[�r�X�C���^�t�F�[�X�B<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3TrialCalcProfitLossCalService<BR>
 * @@author Rajesh Sharma
 * @@version 1.0
 */
public interface WEB3TrialCalcProfitLossCalService extends WEB3BusinessService
{
   /**
    * ���v�v�Z�T�[�r�X���������{����B<BR>
    * <BR>
    * ----<English>--------------------<BR>
    * <BR>
    * Execute the profit and loss calculation service processing. <BR>
    * @@param l_request - ���N�G�X�g�f�[�^�B
    * @@return WEB3GenResponse
    * @@throws
    * �V�X�e�����ʁiweb3-common�j.(web3)�V�X�e�������N���X_common.WEB3BaseException
    * @@roseuid 419060E30227
    */
   public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}
@
