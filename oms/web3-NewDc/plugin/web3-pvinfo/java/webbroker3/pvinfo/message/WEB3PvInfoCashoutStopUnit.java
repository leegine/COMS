head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.04.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoCashoutStopUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o����~���(WEB3PvInfoCashoutStopUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/9/12 �����F(���u) �V�K�쐬
*/
package webbroker3.pvinfo.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�o����~���)<BR>
 * �o����~���N���X<BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3PvInfoCashoutStopUnit extends Message 
{
    
    /**
     * (�o����~������)<BR>
     * �o����~������<BR>
     */
    public String cashoutStopDate;
        
    /**
     * (�o����~�z)<BR>
     * �o����~�z<BR>
     */
    public String cashoutStopAmount;
    
    /**
     * (�o����~���)<BR>
     * �R���X�g���N�^�B<BR>
     */
    public WEB3PvInfoCashoutStopUnit()
    {
        
    }
    
}@
