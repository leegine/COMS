head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.50.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcClientRequestService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X��n����v�Z�T�[�r�X(WEB3TrialCalcClientRequestService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.service.delegate;

import webbroker3.equity.WEB3EquityClientRequestService;
import webbroker3.util.WEB3LogUtility;

/**
 * �i�v�Z�T�[�r�X�N���C�A���g���N�G�X�g�T�[�r�X�j<BR>
 * <BR>
 * �ėp�N���C�A���g���N�G�X�g�T�[�r�X�i�v�Z�T�[�r�X�p�j<BR>
 * <BR>
 * �N���C�A���g����̃��N�G�X�g����������T�[�r�X�̋��ʃX�[�p�[�N���X�B<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * General-purpose client request service (for calculation service)<BR>
 * Common super-class of service that achieves request from client. <BR>
 * @@author Prabhu
 * @@version 1.0
 */
public class WEB3TrialCalcClientRequestService extends WEB3EquityClientRequestService
{
	private static WEB3LogUtility log =
			WEB3LogUtility.getInstance(WEB3TrialCalcClientRequestService.class);

   /**
    * @@roseuid 41C80B5F035C
    */
   public WEB3TrialCalcClientRequestService()
   {
   }

}
@
