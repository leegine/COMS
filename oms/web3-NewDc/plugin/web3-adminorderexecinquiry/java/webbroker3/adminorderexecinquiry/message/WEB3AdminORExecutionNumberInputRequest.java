head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORExecutionNumberInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE������茏���Ɖ���̓��N�G�X�g (WEB3AdminORExecutionNumberInputRequest.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE������茏���Ɖ���̓��N�G�X�g)<BR>
 * <BR>
 * �Ǘ��ҁE������茏���Ɖ���̓��N�G�X�g�N���X<BR>
 * <BR>
 * WEB3AdminORExecutionNumberInputRequest<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORExecutionNumberInputRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_execution_number_input";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * @@roseuid 4212FCFB0111
     */
    public WEB3AdminORExecutionNumberInputRequest()
    {

    }

    /**
     * @@see webbroker3.common.message.WEB3GenRequest#createResponse()
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminORExecutionNumberInputResponse(this);
    }
}
@
