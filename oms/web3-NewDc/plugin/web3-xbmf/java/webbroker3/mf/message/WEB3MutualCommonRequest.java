head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.06.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����M���M�����ʃ��N�G�X�g�N���X(WEB3MutualCommonRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/17 ��O�� (���u) �V�K�쐬
                   2004/08/24 ���� (���u) ���r���[  
*/

package webbroker3.mf.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * �����M���M�����ʃ��N�G�X�g�N���X
 * 
 * @@author ��O��(���u)
 * @@version 1.0 
 */
public class WEB3MutualCommonRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_common";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408170937L;
    
    /**
     * (ID)<BR>
     * ���M���YID <BR>
     * <BR>
     * ���A�抷���̂ݎg�p�B���t����Null<BR>
     */
    public String id;

    /**
     * ���M�����R�[�h<BR>
     * <BR>
     * (��ʂł͔�\��)<BR>
     */
    public String mutualProductCode;
    
    /**
     * �w����@@<BR>
     * <BR>
     * 2:�S���@@3:���z�@@4:����<BR>
     * (���t�̏ꍇ�A�h2:�S���h�͗L�肦�Ȃ�)<BR>
     */
    public String specifyDiv;
    
    /**
     * (���t�E���E����E�抷)����
     */
    public String mutualOrderQuantity;
    
    /**
     * ������<BR>
     * <BR>
     * ���̓��X�|���X�̏����Ŏg�p�����l���i�[����B<BR>
     * �i��ʂł͔�\���j<BR>
     */
    public Date orderedDate;
    
    /**
     * �f�t�H���g�R���X�g���N�^
     * @@roseuid 40A9AB66001B
     */
    public WEB3MutualCommonRequest() 
    {
     
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41065112008C
     */
    public WEB3GenResponse createResponse() 
    {
        return null;
    }
}
@
