head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �A�����̓��X�|���X(WEB3InformInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/24 䈋� (���u) �V�K�쐬
*/
package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (�A�����̓��X�|���X)<BR>
 * �A�����̓��X�|���X�N���X
 */
public class WEB3InformInputResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "inform_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200501251410L;
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��
     */
    public String accountName;
    
    /**
     * (���[���A�h���X)<BR>
     * ���[���A�h���X
     */
    public String mailAddress;
    
    /**
     * @@roseuid 41EE625E0290
     */
    public WEB3InformInputResponse() 
    {
     
    }
    public WEB3InformInputResponse(WEB3InformInputRequest l_request)
    {
        super(l_request);
    }
}
@
