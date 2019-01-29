head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.06.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7444d8857e2568b;
filename	WEB3PvInfoDirectMessageDeleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �_�C���N�g�w�胁�b�Z�[�W�폜���N�G�X�g(WEB3PvInfoDirectMessageDeleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �����F(���u) �V�K�쐬
Revesion History : 2004/10/25 ������(���u) �쐬
*/
package webbroker3.pvinfo.message;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (�_�C���N�g�w�胁�b�Z�[�W�폜���N�G�X�g)<BR>
 * �_�C���N�g�w�胁�b�Z�[�W�폜���N�G�X�g�N���X<BR>
 * @@author �����F
 * @@version 1.00
 */
public class WEB3PvInfoDirectMessageDeleteRequest extends WEB3GenRequest 
{
    
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PvInfoDirectMessageDeleteRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "PvInfo_directMessageDelete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410181349L;
    
    /**
     * (�\�����eID)<BR>
     * �\�����eID<BR>
     */
    public String displayContentsId;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�\�����eID�`�F�b�N<BR>
     * �@@�P�|�P�jthis.�\�����eID == null�̏ꍇ�́A<BR>
     * �@@�@@�@@�@@�@@�u�\�����eID��null�v�̗�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01040<BR>
     * @@roseuid 4147C4090301
     */
    public void validate() throws WEB3BusinessLayerException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�\�����eID�`�F�b�N
        //�P�|�P�jthis.�\�����eID == null�̏ꍇ��.�u�\�����eID��null�v�̗�O���X���[����B        
        if(this.displayContentsId == null)
        {
            log.error(STR_METHOD_NAME + WEB3ErrorCatalog.BUSINESS_ERROR_01040.error_message);
            log.exiting(STR_METHOD_NAME);            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01040,
                getClass().getName() + "." + STR_METHOD_NAME);       
        }
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 41734399034B
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3PvInfoDirectMessageDeleteResponse(this);
    }
}
@
