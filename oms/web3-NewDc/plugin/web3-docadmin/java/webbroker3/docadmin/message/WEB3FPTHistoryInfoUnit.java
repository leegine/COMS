head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3FPTHistoryInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����@@��t�{���������(WEB3FPTHistoryInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/29 ���g (���u) �V�K�쐬
Revision History : 2008/01/25 ���n�m (���u) �d�l�ύX�E���f��No.022
Revision History : 2008/03/17 �g�C�� (���u) �d�l�ύX�E���f��No.045
*/

package webbroker3.docadmin.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;


/**
 * (�����@@��t�{���������)<BR>
 * �����@@��t�{���������N���X<BR>
 *
 * @@author ���g
 * @@version 1.0
 */
public class WEB3FPTHistoryInfoUnit extends Message
{

    /**
     * (���X�R�[�h)<BR>
     * ���X�R�[�h<BR>
     */
    public String branchCode;

    /**
     * (�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h<BR>
     */
    public String acceptCode;

    /**
     * (�ڋq��)<BR>
     * �ڋq��<BR>
     */
    public String acceptName;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * (���ʋ敪)<BR>
     * ���ʋ敪<BR>
     */
    public String documentDiv;

    /**
     * (���ʖ���)<BR>
     * ���ʖ���<BR>
     */
    public String documentNames;

    /**
     * (�d�q�������R�[�h)<BR>
     * �d�q�������R�[�h<BR>
     */
    public String batoProductCode;

    /**
     * (���ʌ�t��)<BR>
     * ���ʌ�t��<BR>
     */
    public Date docuDeliDate;

    /**
     * (���ʎ�ރR�[�h)<BR>
     * ���ʎ�ރR�[�h<BR>
     */
    public String documentCategory;

    /**
     * (���ʎ�ޖ���)<BR>
     * ���ʎ�ޖ���<BR>
     */
    public String documentCategoryName;

    /**
     * (���ʎ�ޒʔ�)<BR>
     * ���ʎ�ޒʔ�<BR>
     */
    public String documentCategoryNumber;

    /**
     * (�폜�t���O)<BR>
     * �폜�t���O<BR>
     */
    public String deleteFlg;

    /**
     * (�݂Ȃ���t��)<BR>
     * �݂Ȃ���t��<BR>
     */
    public Date deemedDeliveryDate;

    /**
     * (�X�V�҃R�[�h)<BR>
     * �X�V�҃R�[�h<BR>
     */
    public String updaterCode;

    /**
     * (�쐬���t)<BR>
     * �쐬���t<BR>
     */
    public Date createDate;

    /**
     * (�X�V���t)<BR>
     * �X�V���t<BR>
     */
    public Date updateTimeStamp;

    /**
     * @@roseuid 46FDDD3D00EA
     */
    public WEB3FPTHistoryInfoUnit()
    {

    }
}
@
