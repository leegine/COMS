head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IPOLotCommonResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ���IPO���I�������ʃ��X�|���X(WEB3IPOLotCommonResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/12/20 �A���� (���u) �V�K�쐬
*/

package webbroker3.ipo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (�Ǘ���IPO���I�������ʃ��X�|���X)<BR>
 * �Ǘ���IPO���I�������ʃ��X�|���X�N���X<BR>
 * 
 * @@author �A����
 * @@version 1.0
 */
public class WEB3IPOLotCommonResponse extends WEB3GenResponse 
{
    /**
      * PTYPE<BR>
      */
    public static final String PTYPE = "IPO_lotCommon";

    /**
      * SerialVersionUID<BR>
      */
    public static final long serialVersionUID = 200512192100L;
    
    /**
     * (�����R�[�h)<BR>
     * IPO�����R�[�h�B
     */
    public String productCode;
    
    /**
     * (������)<BR>
     * IPO�������B
     */
    public String productName;
    
    /**
     * (���I�敪)<BR>
     * �V�K/�J�㒊�I�敪�B<BR>
     * <BR>
     * �P�F�V�K���I<BR>
     * �Q�F�J�㒊�I
     */
    public String lotDiv;
    
    /**
     * @@roseuid 4112E44A0271
     */
    public WEB3IPOLotCommonResponse() 
    {
     
    }
    
    /**
     * (�Ǘ���IPO���I�������ʃ��X�|���X)<BR>
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     * @@roseuid 40DC03E50071
     */
    public WEB3IPOLotCommonResponse(WEB3GenRequest l_request) 
    {
        super(l_request);
    }
}
@
