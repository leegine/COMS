head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.23.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcBreakEvenPointInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X���v����_�v�Z���̓��N�G�X�g(WEB3TrialCalcBreakEvenPointInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�v�Z�T�[�r�X���v����_�v�Z���̓��N�G�X�g�j<BR>
 * <BR>
 * �v�Z�T�[�r�X���v����_�v�Z�T�[�r�X�i���͉�ʕ\���j�̃��N�G�X�g�f�[�^�B<BR>
 * <BR>
 * ----<English>--------------------<BR>
 * <BR>
 * WEB3TrialCalcBreakEvenPointInputRequest<BR>
 * @@author Prabhu
 * @@version 1.0
 *
 * <BR>
 */
public class WEB3TrialCalcBreakEvenPointInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "trialcalc_breakevenpoint_input";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200503301100L;

   /**
    * @@roseuid 41C815A6030E
    */
   public WEB3TrialCalcBreakEvenPointInputRequest()
   {
   }

   /**
    * ���X�|���X�f�[�^���쐬����B<BR>
    * @@return webbroker3.common.message.WEB3GenResponse
    */
   public WEB3GenResponse createResponse()
   {
       return new WEB3TrialCalcBreakEvenPointInputResponse(this);
   }
}
@
