head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.18.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOpenMarginCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�V�K�������������X�|���X�N���X(WEB3FuturesOpenMarginCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ������ �V�K�쐬
*/
package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�����w���敨�V�K�������������X�|���X)<BR>
 * �����w���敨�V�K�������������X�|���X�N���X<BR>
 * 
 * @@author ������
 * @@version 1.0
 */
public class WEB3FuturesOpenMarginCompleteResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_OpenMarginComplete";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201555L;
    
    /**
     * (�X�V����)<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (���ʔԍ�)<BR>
     * ���������h�c<BR>
     */
    public String orderActionId;
    
    /**
     * @@roseuid 40F7AE130128
     */
    public WEB3FuturesOpenMarginCompleteResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
