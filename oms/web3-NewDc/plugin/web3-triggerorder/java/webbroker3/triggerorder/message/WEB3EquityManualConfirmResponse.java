head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.48.39;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityManualConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����蓮�����m�F���X�|���X(WEB3EquityManualConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@鰐V(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

/**
 * (�����蓮�����m�F���X�|���X)<BR>
 * �����蓮�����m�F���X�|���X<BR>
 * 
 * @@author 鰐V
 * @@version 1.0
 */
public class WEB3EquityManualConfirmResponse extends WEB3EquityManualCommonResponse 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_manual_confirm";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603071345L;
    
    /**
     * (�s��I���x���s��R�[�h�ꗗ)<BR>
     * �s��I���x���s��R�[�h�ꗗ<BR>
     * 
     * ����I���x��������\������s��R�[�h�̈ꗗ<BR>
     */
    public String[] messageSuspension = null;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F488920271
     */
    public WEB3EquityManualConfirmResponse() 
    {
     
    }    
}
@
