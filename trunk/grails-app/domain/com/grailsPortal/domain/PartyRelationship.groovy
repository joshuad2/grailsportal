package com.grailsPortal.domain 
class PartyRelationship implements Serializable{
    static mapping = {
         table 'party_relationship'
         child            column:'child_party_id'
         relationshipType column:'relationship_type_id'
         parent           column:'parent_party_id'
    }
    Party            child
    RelationshipType relationshipType
    Party            parent
    static constraints = {
        child(nullable:false)
        relationshipType(nullable:false)
        parent(nullable:false)
    }
    String toString() {
        return parent.firstName+" "+parent.lastName+"-"+child.firstName+" "+child.lastName
    }
}
