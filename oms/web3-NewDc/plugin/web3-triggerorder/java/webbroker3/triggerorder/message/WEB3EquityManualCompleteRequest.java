head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityManualCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����蓮�����������N�G�X�g(WEB3EquityManualCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@鰐V(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

/**
 * (�����蓮�����������N�G�X�g)<BR>
 * �����蓮�����������N�G�X�g<BR>
 * 
 * @@author 鰐V
 * @@version 1.0
 */
public class WEB3EquityManualCompleteRequest extends WEB3ManualCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_manual_complete";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603071345L;

    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password = null;

    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F488920196
     */
    public WEB3EquityManualCompleteRequest() 
    {
     
    }    
}
@
