head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.05.27;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoAccountInformationUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ώیڋq���(WEB3PvInfoAccountInformationUnit.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
*/
package webbroker3.pvinfo.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�Ώیڋq���)<BR>
 * �Ώیڋq���N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoAccountInformationUnit extends Message 
{
    
    /**
     * (���X�R�[�h)<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     */
    public String accountCode;
    
    /**
     * (�ŏI�{������)<BR>
     */
    public Date lastBrowseDate;
}
@
