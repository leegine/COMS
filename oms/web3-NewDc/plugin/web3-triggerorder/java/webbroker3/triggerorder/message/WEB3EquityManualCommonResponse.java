head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.47.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3EquityManualCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����蓮�������ʃ��X�|���X(WEB3EquityManualCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/06�@@鰐V(���u) �V�K�쐬
*/

package webbroker3.triggerorder.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (�����蓮�������ʃ��X�|���X)<BR>
 * �����蓮�������ʃ��X�|���X<BR>
 * 
 * @@author 鰐V
 * @@version 1.0
 */
public class WEB3EquityManualCommonResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "equity_manual_common";
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200603071345L;
    
    /**
     * (�����蓮����Unit)<BR>
     * �����蓮����Unit<BR>
     */
    public WEB3EquityManualUnit[] manualUnits;
    
    /**
     * �R���X�g���N�^<BR>
     * @@roseuid 43F488920271
     */
    public WEB3EquityManualCommonResponse() 
    {
     
    }
    
}
@
