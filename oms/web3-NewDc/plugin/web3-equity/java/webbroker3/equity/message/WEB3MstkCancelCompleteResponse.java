head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkCancelCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j������������������X�|���X(WEB3MstkCancelCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 �J�N���V (���u) �V�K�쐬
                   2004/12/10 �K�� (SRA) �C��
*/

package webbroker3.equity.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * �i�����~�j������������������X�|���X�j�B<BR>
 * <BR>
 * �����~�j������������������X�|���X�N���X
 * @@author �J�N���V
 * @@version 1.0
 */
public class WEB3MstkCancelCompleteResponse extends WEB3GenResponse 
{
    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "mstk_cancelComplete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * (�X�V����)<BR>
     */
    public Date lastUpdatedTimestamp;
    
    /**
     * (���ʔԍ�)<BR>
     */
    public String orderActionId;
    
    /**
     * @@roseuid 4167B04E0002
     */
	public WEB3MstkCancelCompleteResponse()
	{
           
	}
    public WEB3MstkCancelCompleteResponse(WEB3GenRequest l_request)
    {
        
        super(l_request);
           
    }
}@
