head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkProductCodeNameUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����~�j�����������Ɖ���P��(WEB3MstkExecuteGroup.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12  �d��(���u) �V�K�쐬
*/
package webbroker3.equity.message;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * �i�����~�j���������R�[�h���́j�B<BR>
 * <br>
 * �����~�j���������R�[�h����
 * @@author �d��
 * @@version 1.0 
 */
public class WEB3MstkProductCodeNameUnit extends Message 
{
    
    /**
     * (�����R�[�h)<BR>
     */
    public String productCode;
    
    /**
     * (������)<BR>
     */
    public String productName;
    
    /**
     * (�����~�j���������R�[�h����)<BR>
     * �f�t�H���g�R���X�g���N�^�B
     */
    public WEB3MstkProductCodeNameUnit() 
    {
     
    }
}
@
