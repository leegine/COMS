head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.26.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AioAbstractStatusUtility.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����󋵂��擾�̏����N���X(WEB3AioAbstractStatusUtility.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/28 ����� (���u)  �V�K�쐬
                   
*/
package webbroker3.aio;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * �����󋵂��擾�̏����N���X�B<BR>
 * 
 * @@author ����� (���u)
 * @@version 1.0
 */
public abstract class WEB3AioAbstractStatusUtility
{
    /**
     * �ڂ��������X�e�[�^�X�J�������擾
     * 
     * @@return Map
     */
    protected abstract Map getStatusMap();
    
    /**
     * �L�[�Ή��̏����X�e�[�^�X���擾
     * @@param l_status - �L�[
     * @@return
     */
    protected String getStatus(Status l_status)
    {
        if (l_status == null)
        {
            return null;
        }
        
        Map l_statusMap = getStatusMap();
        
        if (l_statusMap == null)
        {
            return null;
        }
        
        Set l_set = l_statusMap.keySet();
        Status[] l_statuses = new Status[l_set.size()];
        
        Iterator l_itr = l_statusMap.keySet().iterator();
        int i = 0;
        while (l_itr.hasNext())
        {            
            l_statuses[i] = (Status)l_itr.next();
            i++;
        }
    
        Status l_matchStatus = l_status.match(l_statuses);
        
        if (l_matchStatus == null)
        {
            return null;
        }
            
        return (String)l_statusMap.get(l_matchStatus);        
    }

    /**
     * �L�[ �C���^�t�F�[�X
     * 
     * @@author ����� (���u)
     * @@version 1.0
     */
    public static interface Status
    {
        public Status match(Status[] l_statuses);
    }
     
    /**
     * �f�t�H���g�L�[�C���^�t�F�[�X�����N���X
     * 
     * @@author ����� (���u)
     * @@version 1.0
     */
    public static class DefaultStatus implements Status
    {
        /**
         * ���o���X�e�[�^�X�\���\�ɑΉ���'���̑�'�̕\��
         */
        public static final String ANY = "<ANY>";
        
        /**
         * ���o���X�e�[�^�X�\���\�ɑΉ���'-'�̕\��
         */
        public static final String OTHER = "<OTHER>";
        
        /**
         * 
         */
        private String[] params;
        
        /**
         * ���o���X�e�[�^�X�\���\�̊e��������
         * 
         * @@param l_strParams 
         */
        public DefaultStatus(String[] l_strParams)
        {
            this.params = l_strParams;
        }
        
        /**
         * �X�e�[�^�X�\���琮���̃X�e�[�^�X�N���X���擾
         * 
         * @@param l_statuses - �ڂ��������X�e�[�^�X�J����
         */
        public Status match(Status[] l_statuses)
        {
            if (l_statuses == null)
            {
                return null;
            }
            
            if (this.params == null)
            {
                return null;
            }
            
            List l_lisStatues = Arrays.asList(l_statuses);
            List l_lisMatchStatues = null;
            List l_lisOtherStatues = null;
                        
            for (int i = 0; i < this.params.length; i++)
            {                   
                l_lisMatchStatues = new Vector();
                l_lisOtherStatues = new Vector();
                boolean l_blnAccurateMatch = false;
                
                for (int j = 0; j < l_lisStatues.size(); j++)
                {
                    Object l_obj = l_lisStatues.get(j);
                    if (!(l_obj instanceof DefaultStatus))
                    {
                        continue;
                    }
                    
                    DefaultStatus l_status = (DefaultStatus)l_obj;
                    
                    if (l_status.params == null)
                    {
                        continue;
                    }
                                        
                    if (equals(this.params[i], l_status.params[i]))
                    {
                        l_lisMatchStatues.add(l_status);
                        l_blnAccurateMatch = true;
                    }
                    else if (ANY.equals(l_status.params[i]))
                    {
                        l_lisMatchStatues.add(l_status);
                    }
                    else if (OTHER.equals(l_status.params[i]))
                    {
                        l_lisOtherStatues.add(l_status);
                    }
                    
                }
                
                if (!l_blnAccurateMatch)
                {
                    l_lisMatchStatues.addAll(l_lisOtherStatues);
                }
                
                l_lisStatues = l_lisMatchStatues;                

            }
            
            if (l_lisStatues.size() == 0)
            {
                return null;
            }
            else
            {
                return (Status)l_lisStatues.get(0);
            }

        }
        
        /**
         * equals���b�\�g��override
         * 
         * @@param l_obj1
         * @@param l_obj2
         * @@return
         */
        private boolean equals(Object l_obj1, Object l_obj2)
        {
            if (l_obj1 == null && l_obj2 == null)
            {
                return true;
            }
        
            if (l_obj1 == null || l_obj2 == null)
            {
                return false;
            }
        
            return l_obj1.equals(l_obj2);
        }
        
        /**
         * toString���b�\�g��override
         */
        public String toString()
        {
            if (this.params == null)
            {
                return "{}";
            }
            
            StringBuffer l_sb = new StringBuffer();
            l_sb.append("{");
            
            for (int i = 0; i < this.params.length; i++)
            {
                l_sb.append(" [" + i + "]=" + this.params[i]);
            }
            
            l_sb.append(" }");
            
            return l_sb.toString();            
        }

    }
    
    
    public static void main(String[] args)
    {
        class StatusUtils extends WEB3AioAbstractStatusUtility
        {

            public Map getStatusMap()
            {
                Map l_map = new Hashtable();
                
                l_map.put(new DefaultStatus(new String[] {"1", "2", "3", "4", "5", "6", "7"}), "1");
                l_map.put(new DefaultStatus(new String[] {"1", DefaultStatus.OTHER, "3",  DefaultStatus.ANY,  DefaultStatus.ANY,  DefaultStatus.ANY,  DefaultStatus.ANY}), "2");
                l_map.put(new DefaultStatus(new String[] {"1", DefaultStatus.OTHER, "4",  DefaultStatus.ANY,  DefaultStatus.ANY,  DefaultStatus.ANY,  DefaultStatus.ANY}), "3");
                l_map.put(new DefaultStatus(new String[] {"2", "2", "4",  DefaultStatus.ANY,  DefaultStatus.ANY,  DefaultStatus.ANY,  DefaultStatus.ANY}), "4");
                l_map.put(new DefaultStatus(new String[] {"2", "3", DefaultStatus.ANY, DefaultStatus.ANY, DefaultStatus.ANY, DefaultStatus.ANY, DefaultStatus.ANY}), "5");
                l_map.put(new DefaultStatus(new String[] {DefaultStatus.OTHER, "3",  DefaultStatus.ANY,  DefaultStatus.ANY,  DefaultStatus.ANY,  DefaultStatus.ANY,  DefaultStatus.ANY}), "6");
                l_map.put(new DefaultStatus(new String[] {DefaultStatus.OTHER, DefaultStatus.OTHER, DefaultStatus.ANY, DefaultStatus.ANY, DefaultStatus.ANY, DefaultStatus.ANY , DefaultStatus.ANY}), "7");
                
                l_map.put(new DefaultStatus(new String[] {"1", "2", "3", "4", DefaultStatus.OTHER, null, null}), "9");
                l_map.put(new DefaultStatus(new String[] {"1", "2", "3", "4", null, null, null}), "8");
                
                
                return l_map;
            }
            
            public String getStatus(String l_strA, String l_strB, String l_strC, String l_strD, String l_strE, String l_strF, String l_strG)
            {
                return getStatus(new DefaultStatus(new String[] {l_strA, l_strB, l_strC, l_strD, l_strE, l_strF, l_strG}));
            }
            
        }
        
        
        StatusUtils l_statusUtils = new StatusUtils();
        
        System.out.println(l_statusUtils.getStatus("1", "2", "3", "4", null, null, null));
        
    }
    
    
            
}
@
