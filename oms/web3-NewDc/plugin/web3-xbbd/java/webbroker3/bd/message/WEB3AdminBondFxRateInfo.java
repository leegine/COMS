head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondFxRateInfo.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �בփ��[�g(WEB3AdminBondFxRateInfo.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/17 ��іQ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�בփ��[�g)<BR>
 * �בփ��[�g�N���X
 * 
 * @@author ��іQ
 * @@version 1.0
 */
public class WEB3AdminBondFxRateInfo  extends Message
{
    
    /**
     * (���t��ב�)<BR>
     * ���t��ב�
     */
    public String buyBaseFx;
    
    /**
     * (���p��ב�)<BR>
     * ���p��ב�
     */
    public String sellBaseFx;
    
    /**
     * (���t���ב�)<BR>
     * ���t���ב�
     */
    public String buyExecFx;
    
    /**
     * (���p���ב�)<BR>
     * ���p���ב�
     */
    public String sellExecFx;
    
    /**
     * @@roseuid 44E336380290
     */
    public WEB3AdminBondFxRateInfo() 
    {
     
    }
}
@
