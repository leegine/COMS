head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.45.16;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminORExecutionNumberReferenceResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҁE������茏���Ɖ�X�|���X (WEB3AdminORExecutionNumberReferenceResponse.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.adminorderexecinquiry.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ��ҁE������茏���Ɖ�X�|���X)<BR>
 * <BR>
 * �Ǘ��ҁE������茏���Ɖ�X�|���X�N���X<BR>
 * <BR>
 * WEB3AdminORExecutionNumberReferenceResponse<BR>
 * <BR>
 * @@author Sudhindrakinnal
 * @@version 1.0
 */
public class WEB3AdminORExecutionNumberReferenceResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "admin_or_execution_number_reference";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200502011606L;

    /**
     * �i�������ʌ������ꗗ�j<BR>
     * <BR>
     */
    public WEB3AdminOROrderDayNumberInfoUnit[] orderBizDateCountInfoList;

    /**
     * @@roseuid 4212FD140027
     */
    public WEB3AdminORExecutionNumberReferenceResponse()
    {

    }
}@
