head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.07.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoStopInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��~���ꗗUnit(WEB3AccInfoStopInfoUnit)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/09/19 ������ (���u) �V�K�쐬
*/
package webbroker3.accountinfo.message;


/**
 * (��~���ꗗUnit)<BR>
 * ��~���ꗗUnit<BR>
 * <BR>
 * @@author ������<BR>
 * @@version 1.0<BR>
 */
public class WEB3AccInfoStopInfoUnit extends WEB3AccInfoStopInfo 
{
    /**
     * (�R���X�g���N�^)
     *�R���X�g���N�^
     */
    public WEB3AccInfoStopInfoUnit()
    {
        
    }
    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String accountCode;
    
    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     */
    public String accountName;
    
    /**
     * (�Ǘ���ID)<BR>
     * �Ǘ���ID<BR>
     */
    public String managerID;
}
@
