head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.09.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLProductInfoUnit.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �S�ۖ����������N���X(WEB3SLProductInfoUnit.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 �����F (���u) �V�K�쐬 ���f��760
Revision History : 2007/11/08 �g�E�N�| (���u) ���f��822
*/

package webbroker3.aio.message;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.message.Message;

/**
 * (�S�ۖ����o�^���)<BR>
 * �S�ۖ����o�^���<BR>
 *
 * @@author �����F
 * @@version 1.0
 */
public class WEB3SLProductInfoUnit extends Message
{
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public long productId;

    /**
     * (�����R�[�h)<BR>
     * �����R�[�h<BR>
     */
    public String productCode;

    /**
     * (�����^�C�v)<BR>
     * �����^�C�v<BR>
     * <BR>
     * 0�F���̑�<BR>
     * 1�F����<BR>
     * 2�F��<BR>
     * 3�F�����M��<BR>
     * 4�F�O����<BR>
     * 5�F����<BR>
     * 6�F�敨�I�v�V����<BR>
     * 7�F�ݐϓ���<BR>
     */
    public String productType;

    /**
     * (������)<BR>
     * ������<BR>
     */
    public String productName;

    /**
     * (�K�i�敪)<BR>
     * �K�i�敪<BR>
     * <BR>
     * 0�F�s�K�i<BR>
     * 1�F�K�i<BR>
     */
    public String qualifiedDiv;

    /**
     * (�|��)<BR>
     * �|��<BR>
     */
    public String weight;

    /**
     * (�K�p����From)<BR>
     * �K�p����From<BR>
     */
    public Date targetPeriodFrom;

    /**
     * (�K�p����To)<BR>
     * �K�p����To<BR>
     */
    public Date targetPeriodTo;

    /**
     * (���R)<BR>
     * ���R<BR>
     */
    public String reason;

    /**
     * (�S�ۖ����o�^���)<BR>
     * �R���X�g���N�^<BR>
     * @@roseuid 46DBBB800130
     */
    public WEB3SLProductInfoUnit()
    {

    }
}
@
